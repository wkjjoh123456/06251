package com.zjt.model;


import com.zjt.entity.Dfoodpar;

public class Dfoodtotallist {
        private Integer id;
        private Integer fIsquick;
        private Integer fParentid;
        private Long fPro;
        private Long fCar;
        private Long fFat;
        private Long fEne;
        private String fName;

    public Integer getFpId() {
        return fpId;
    }

    public String getFpName() {
        return fpName;
    }

    private Integer fpId;
    public Dfoodpar getDfoodpar() {
        return dfoodpar;
    }

    public void setDfoodpar(Dfoodpar dfoodpar) {
        this.dfoodpar = dfoodpar;
    }
    private Dfoodpar dfoodpar;

    private String fpName;
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public Integer getfIsquick() {
            return fIsquick;
        }
        public void setfIsquick(Integer fIsquick) {
            this.fIsquick = fIsquick;
        }
        public Integer getfParentid() {
            return fParentid;
        }
        public void setfParentid(Integer fParentid) {
            this.fParentid = fParentid;
        }
        public Long getfPro() {
            return fPro;
        }
        public void setfPro(Long fPro) {
            this.fPro = fPro;
        }
        public Long getfCar() {
            return fCar;
        }
        public void setfCar(Long fCar) { this.fCar = fCar; }
        public Long getfFat() {
            return fFat;
        }
        public void setfFat(Long fFat) {
            this.fFat = fFat;
        }
        public Long getfEne() {
            return fEne;
        }
        public void setfEne(Long fEne) {
            this.fEne = fEne;
        }
        public String getfName() {
            return fName;
        }
        public void setfName(String fName) {
            this.fName = fName == null ? null : fName.trim();
        }
    }
