package com.example.internship;

public class Record1
{
    //String name,school,class1,birth_date, add,mobile,parent_mobile, email,pass,interest,want_bacome, refer,join_date, enquiry;
    public String name;
    public String school;
    public String add;
    public String mobile;
    public String parent_mobile;
    public String email;
    public String interest;
    public String want_bacome;

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    public String getAdd() {
        return add;
    }

    public String getMobile() {
        return mobile;
    }

    public String getParent_mobile() {
        return parent_mobile;
    }

    public String getEmail() {
        return email;
    }


    public String getInterest() {
        return interest;
    }

    public String getWant_bacome() {
        return want_bacome;
    }

    public String getRefer() {
        return refer;
    }

    public String getJoin_date() {
        return join_date;
    }

    public String getEnquiry() {
        return enquiry;
    }

    public String refer;
    public String join_date;
    public String enquiry;
    public Record1(){}
    public Record1(String name, String school, String add, String mobile, String parent_mobile,
                   String email, String interest, String want_bacome, String refer, String join_date, String enquiry) {
        this.name = name;
        this.school = school;
        this.add = add;
        this.mobile = mobile;
        this.parent_mobile = parent_mobile;
        this.email = email;
        this.interest = interest;
        this.want_bacome = want_bacome;
        this.refer = refer;
        this.join_date = join_date;
        this.enquiry = enquiry;
    }
}
