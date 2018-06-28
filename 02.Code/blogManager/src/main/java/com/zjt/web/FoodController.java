package com.zjt.web;



import com.github.pagehelper.PageHelper;
import com.zjt.entity.Dfoodlist;
import com.zjt.model.Dfoodtotallist;
import com.zjt.model.JqgridBean;
import com.zjt.model.PageRusult;
import com.zjt.service.DfoodService;
import com.zjt.util.PageBean;
import com.zjt.util.ResponseUtil;
import com.zjt.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/admin/food")
public class FoodController {

    @Resource
    private DfoodService dfoodService;


    @RequestMapping("/tofoodmanage")
    @RequiresPermissions(value = {"食品录入"})
    public String tofoodmanager() {
        return "food/food2";
    }

    /**
     * 分页查询食品信息
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    @RequiresPermissions(value = {"食品录入"})
    public Map<String, Object> list(JqgridBean jqgridbean
                    /*String userName,@RequestParam(value="page",required=false)Integer page*/
    ) throws Exception {
        LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
        LinkedHashMap<String, Object> datamap = new LinkedHashMap<String, Object>();
        Example dfoodExample = new Example(Dfoodlist.class);
        Example.Criteria criteria = dfoodExample.or();
        criteria.andNotEqualTo("fName","admin");

        if (StringUtils.isNotEmpty(jqgridbean.getSearchField())) {
            if ("fname".equalsIgnoreCase(jqgridbean.getSearchField())) {
                if ("eq".contentEquals(jqgridbean.getSearchOper())) {
                    criteria.andEqualTo("fName",jqgridbean.getSearchString());
                }
            }
        }

        if(StringUtils.isNotEmpty(jqgridbean.getSidx())&&StringUtils.isNotEmpty(jqgridbean.getSord())){
            dfoodExample.setOrderByClause(jqgridbean.getSidx() + " " + jqgridbean.getSord());
        }

        PageHelper.startPage(jqgridbean.getPage(), jqgridbean.getLength());
        List<Dfoodlist> foodList = dfoodService.selectByExample(dfoodExample);
        PageRusult<Dfoodlist> pageRusult =new PageRusult<Dfoodlist>(foodList);


       /* Integer totalrecords = userService.selectCountByExample(tuserExample);//总记录数
        Page pagebean = new Page(jqgridbean.getLength() * ((jqgridbean.getPage() > 0 ? jqgridbean.getPage() : 1) - 1), jqgridbean.getLength(), totalrecords);
        tuserExample.setPage(pagebean);
        tuserExample.setOrderByClause(jqgridbean.getSidx() + " " + jqgridbean.getSord());
        List<Tuser> userList = userService.selectByExample(tuserExample);*/




        resultmap.put("currpage", String.valueOf(pageRusult.getPageNum()));
        resultmap.put("totalpages", String.valueOf(pageRusult.getPages()));
        resultmap.put("totalrecords", String.valueOf(pageRusult.getTotal()));
        resultmap.put("datamap", foodList);

        return resultmap;
    }
    @RequestMapping(value="/qlist",method = RequestMethod.POST)
    public String list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Dfoodlist dfoodlist, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fName", StringUtil.formatLike(dfoodlist.getfName()));

        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPagesize());
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
        System.out.println("123");
        List<Dfoodtotallist>  dfoodtotallists = dfoodService.findfood(map);

        long total = dfoodService.getTotalfood(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(dfoodtotallists);
        result.put("rows",jsonArray);
        result.put("total",total);
        ResponseUtil.write(response,result);


        return null;
    }
}
