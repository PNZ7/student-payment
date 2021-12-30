package edu.javacourse.student.domain;

import javax.persistence.*;
@Entity
@Table(name = "jc_register_office")
public class RegisterOffice {

        @Id
        @Column(name = "r_office_id")
        private Long registerOfficeId;
        @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
        @JoinColumn(name = "r_office_area_id")
        private CountryArea countryArea;
        @Column(name = "r_office_name")
        private String registerOffice;

    public Long getRegisterOfficeId() {
        return registerOfficeId;
    }

    public void setRegisterOfficeId(Long registerOfficeId) {
        this.registerOfficeId = registerOfficeId;
    }

    public CountryArea getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(CountryArea countryArea) {
        this.countryArea = countryArea;
    }

    public String getRegisterOffice() {
        return registerOffice;
    }

    public void setRegisterOffice(String registerOffice) {
        this.registerOffice = registerOffice;
    }
}
