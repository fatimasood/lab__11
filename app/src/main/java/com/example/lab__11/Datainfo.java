package com.example.lab__11;

import android.widget.TextView;

public class Datainfo {
        private String name;
        private String ContactNumber;
        private String blood;
        private String City;

        public Datainfo() {

        }

        public String getName() {
            return name;
        }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }



        public String getContactNumber() {
            return ContactNumber;
        }

        public void setContactNumber(String ContactNumber) {
            this.ContactNumber = ContactNumber;
        }

        public String getBlood() {
            return blood;
        }

        public void setBloodGroup(String blood) {
            this.blood = blood;
        }
    }

