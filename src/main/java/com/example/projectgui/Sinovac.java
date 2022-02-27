package com.example.projectgui;

import java.sql.SQLException;
import java.util.ArrayList;

public class Sinovac extends Vaccines implements second_dose,third_dose{


    @Override
    public String schedule_seconddose(String first_dose_date) throws SQLException {
        //separate day, month, year
        String[] first_date = first_dose_date.split("-");
        ArrayList<Integer> first_date_int=new ArrayList<>();

        //the separated day,month,year are still String, so we convert to int
        for(String a:first_date){
            int i = Integer.parseInt(a);
            first_date_int.add(i); // add the converted one to a new array
            //"20/2/2021"
            //the format will be [20,2,2021]
        }
        int second_year = first_date_int.get(0);
        int second_month = first_date_int.get(1);
        int second_day = first_date_int.get(2)+21;


        switch (second_month){//switch (month)
            case 1,3,5,7,8,10,12->{//months with 31 days
                first_date_int.clear();
                if (second_day>31){//if the second appointmnent isnt in the same month
                    int day_nextapp = second_day-31;
                    int month_nextapp = second_month+1;
                    first_date_int.add(0,day_nextapp);
                    if(month_nextapp>12){ //if the appointment for second dose is next year
                        int year_nextapp = second_year+1;
                        first_date_int.add(1,1);
                        first_date_int.add(2,year_nextapp);
                    }else {//if not then just add the same year to an array
                        first_date_int.add(1,month_nextapp);
                        first_date_int.add(2,second_year);
                    }

                }else {
                    first_date_int.add(0,second_day);
                    first_date_int.add(1,second_month);
                    first_date_int.add(2,second_year);
                }
            }
            case 4,6,9,11->{//months with 30 days
                first_date_int.clear();
                if (second_day>30){
                    int day_nextapp = second_day-30;
                    int month_nextapp = second_month+1;
                    first_date_int.add(0,day_nextapp);

                    if(month_nextapp>12){ //if the appointment for second dose is next year
                        int year_nextapp = second_year+1;
                        first_date_int.add(1,1);
                        first_date_int.add(2,year_nextapp);
                    }else {//if not then just add the same year to an array
                        first_date_int.add(1,month_nextapp);
                        first_date_int.add(2,second_year);
                    }


                }else {
                    first_date_int.add(0,second_day);
                    first_date_int.add(1,second_month);
                    first_date_int.add(2,second_year);
                }
            }
            case 2->{//special month with 28/29 days
                first_date_int.clear();
                if (second_day>28){
                    int day_nextapp =second_day-28;
                    int month_nextapp = second_month+1;
                    first_date_int.add(0,day_nextapp);
                    if(month_nextapp>12){ //if the appointment for second dose is next year
                        int year_nextapp = second_year+1;
                        first_date_int.add(1,1);
                        first_date_int.add(2,year_nextapp);
                    }else {//if not then just add the same year to an array
                        first_date_int.add(1,month_nextapp);
                        first_date_int.add(2,second_year);
                    }


                }else {
                    first_date_int.add(0,second_day);
                    first_date_int.add(1,second_month);
                    first_date_int.add(2,second_year);
                }
            }
        }
        String second_date=first_date_int.get(2)+"-"+first_date_int.get(1)+"-"+first_date_int.get(0);
        return second_date;
    }


    @Override
    public String schedule_thirddose(String second_dose_date) throws SQLException {
        //separate day, month, year
        String[] second_date = second_dose_date.split("-");
        ArrayList<Integer> second_date_int=new ArrayList<>();

        //the separated day,month,year are still String, so we convert to int
        for(String a:second_date){
            int i = Integer.parseInt(a);
            second_date_int.add(i); // add the converted one to a new array
            //"20/2/2021"
            //the format will be [20,2,2021]
        }
        int third_year = second_date_int.get(0);
        int third_month = second_date_int.get(1);
        int third_day = second_date_int.get(2)+21;


        switch (third_month){//switch (month)
            case 1,3,5,7,8,10,12->{//months with 31 days
                second_date_int.clear();
                if (third_day>31){//if the second appointmnent isnt in the same month
                    int day_nextapp = third_day-31;
                    int month_nextapp = third_month+1;
                    second_date_int.add(0,day_nextapp);
                    if(month_nextapp>12){ //if the appointment for second dose is next year
                        int year_nextapp = third_year+1;
                        second_date_int.add(1,1);
                        second_date_int.add(2,year_nextapp);
                    }else {//if not then just add the same year to an array
                        second_date_int.add(1,month_nextapp);
                        second_date_int.add(2,third_year);
                    }

                }else {
                    second_date_int.add(0,third_day);
                    second_date_int.add(1,third_month);
                    second_date_int.add(2,third_year);
                }
            }
            case 4,6,9,11->{//months with 30 days
                second_date_int.clear();
                if (third_day>30){
                    int day_nextapp = third_day-30;
                    int month_nextapp = third_month+1;
                    second_date_int.add(0,day_nextapp);

                    if(month_nextapp>12){ //if the appointment for second dose is next year
                        int year_nextapp = third_year+1;
                        second_date_int.add(1,1);
                        second_date_int.add(2,year_nextapp);
                    }else {//if not then just add the same year to an array
                        second_date_int.add(1,month_nextapp);
                        second_date_int.add(2,third_year);
                    }


                }else {
                    second_date_int.add(0,third_day);
                    second_date_int.add(1,third_month);
                    second_date_int.add(2,third_year);
                }
            }
            case 2->{//special month with 28/29 days
                second_date_int.clear();
                if (third_day>28){
                    int day_nextapp =third_day-28;
                    int month_nextapp = third_month+1;
                    second_date_int.add(0,day_nextapp);
                    if(month_nextapp>12){ //if the appointment for second dose is next year
                        int year_nextapp = third_year+1;
                        second_date_int.add(1,1);
                        second_date_int.add(2,year_nextapp);
                    }else {//if not then just add the same year to an array
                        second_date_int.add(1,month_nextapp);
                        second_date_int.add(2,third_year);
                    }


                }else {
                    second_date_int.add(0,third_day);
                    second_date_int.add(1,third_month);
                    second_date_int.add(2,third_year);
                }
            }
        }
        String third_date=second_date_int.get(2)+"-"+second_date_int.get(1)+"-"+second_date_int.get(0);
        return third_date;
    }
}
