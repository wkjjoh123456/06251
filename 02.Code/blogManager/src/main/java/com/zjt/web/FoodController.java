package com.zjt.web;



import com.github.pagehelper.PageHelper;
import com.zjt.entity.Dfoodlist;
import com.zjt.entity.Dfoodpar;
import com.zjt.model.Dfoodtotallist;
import com.zjt.model.JqgridBean;
import com.zjt.model.PageRusult;
import com.zjt.service.DfoodService;


import com.zjt.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/admin/food")
public class FoodController {

    @Resource
    private DfoodService dfoodService;


/*页面跳转   food2.ftl  easyui分页*/
    @RequestMapping("/tofoodmanage")
    @RequiresPermissions(value = {"食品录入"})
    public String tofoodmanager() {
        return "food/food2";
    }
    @RequestMapping("/tofoodkind")
    @RequiresPermissions(value = {"食品类别"})
    public String tofoodkind() {
        return "food/foodkind";
    }

    /**
     * jqgrid  分页查询食品信息(food.ftl)
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
    /*easyui 分页 查询  food2.ftl使用中*/
    @RequestMapping(value="/qlist",method = RequestMethod.POST)
    public String list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Dfoodlist dfoodlist, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fName", StringUtil.formatLike(dfoodlist.getfName()));
        map.put("fIsquick", (dfoodlist.getfIsquick()));
        map.put("fParentid", (dfoodlist.getfParentid()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPagesize());
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
        List<Dfoodtotallist>  dfoodtotallists = dfoodService.findfood(map);
        long total = dfoodService.getTotalfood(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(dfoodtotallists);
        result.put("rows",jsonArray);
        result.put("total",total);
        ResponseUtil.write(response,result);
        return null;
    }

    /*查询食物父级id*/
    @RequestMapping(value="/getParentid")
    public String queryParentid(HttpServletResponse response) throws Exception{
        List<Dfoodpar> dfoodpars = dfoodService.selectParentId();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(dfoodpars);
        jsonObject.put("rows",jsonArray);
        System.out.println(jsonArray);
        ResponseUtil.write(response,jsonArray);
        return null;
    }
/*食物添加*/
    @RequestMapping(value="",method =RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> Add(@RequestBody Dfoodlist dfoodlist){
        Map<String,Object> map=new HashMap<String,Object>();
        int resultTotal = 0;
        resultTotal = dfoodService.addFood(dfoodlist);
        if (resultTotal > 0) {
            map.put("success", true);
            return map;
        } else {
            map.put("success", false);
            return map;
        }
    }

/*食物删除*/
    @RequestMapping(value="delete/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public  Map<String,Object> Delete(@PathVariable(value ="ids") String ids)
    {
        Map<String,Object> map=new HashMap<String,Object>();
        String[] idsstr = ids.split(",");
        System.out.println(idsstr);
        for (int i = 0; i < idsstr.length; i++) {
            dfoodService.deleteFood(idsstr[i]);
        }
        map.put("success", true);
        return map;
    }
/*食物修改*/
    @RequestMapping(value="",method =RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> update(@RequestBody Dfoodlist dfoodlist){
        Map<String,Object> map=new HashMap<String,Object>();
        int resultTotal = dfoodService.updateFood(dfoodlist);
        if (resultTotal > 0) {
            map.put("success", true);
            return map;
        } else {
            map.put("success", false);
            return map;
        }
    }

    /*easyui 分页 查询  foodkind.ftl使用中*/
    @RequiresPermissions(value = {"食品类别"})
    @RequestMapping(value="/foodkindlist",method = RequestMethod.POST)
    public String foodkindlist(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Dfoodpar dfoodpar, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fpName", StringUtil.formatLike(dfoodpar.getFpName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPagesize());
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
        List<Dfoodpar>  dfoodtotalkindlists = dfoodService.findFoodKind(map);
        long total = dfoodService.getTotalFoodKind(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(dfoodtotalkindlists);
        result.put("rows",jsonArray);
        result.put("total",total);
        ResponseUtil.write(response,result);
        return null;
    }
@PostMapping(value = "/addFoodKind")
    @ResponseBody
    public Map<String,Object> addFoodKind(@RequestBody Dfoodpar dfoodpar){
        Map<String, Object> map = new HashMap<String, Object>();
        int resultTotal = 0;
        resultTotal = dfoodService.addFoodKind(dfoodpar);
        if (resultTotal > 0) {
            map.put("success", true);
            return map;
        } else {
            map.put("success", false);
            return map;
        }
    }
    @PutMapping(value = "/addFoodKind")
    @ResponseBody
    public Map<String,Object> updateFoodKind(@RequestBody Dfoodpar dfoodpar){
        Map<String, Object> map = new HashMap<String, Object>();
        int resultTotal = 0;
        resultTotal = dfoodService.updateFoodKind(dfoodpar);
        if (resultTotal > 0) {
            map.put("success", true);
            return map;
        } else {
            map.put("success", false);
            return map;
        }
    }

    /*食物删除*/
    @RequestMapping(value="deleteFoodKind/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public  Map<String,Object> deleteFoodKind(@PathVariable(value ="ids") String ids)
    {
        Map<String,Object> map=new HashMap<String,Object>();
        String[] idsstr = ids.split(",");
        System.out.println(idsstr);
        for (int i = 0; i < idsstr.length; i++) {
            dfoodService.deleteFoodKind(idsstr[i]);
        }
        map.put("success", true);
        return map;
    }
}
