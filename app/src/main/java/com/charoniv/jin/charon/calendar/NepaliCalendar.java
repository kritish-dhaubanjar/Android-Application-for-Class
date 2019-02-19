package com.charoniv.jin.charon.calendar;

public class NepaliCalendar {

    final int [][] bs = {
            {2000,30,32,31,32,31,30,30,30,29,30,29,31},
            {2001,31,31,32,31,31,31,30,29,30,29,30,30},
            {2002,31,31,32,32,31,30,30,29,30,29,30,30},
            {2003,31,32,31,32,31,30,30,30,29,29,30,31},
            {2004,30,32,31,32,31,30,30,30,29,30,29,31},
            {2005,31,31,32,31,31,31,30,29,30,29,30,30},
            {2006,31,31,32,32,31,30,30,29,30,29,30,30},
            {2007,31,32,31,32,31,30,30,30,29,29,30,31},
            {2008,31,31,31,32,31,31,29,30,30,29,29,31},
            {2009,31,31,32,31,31,31,30,29,30,29,30,30},
			{2010,31,31,32,32,31,30,30,29,30,29,30,30},
			{2011,31,32,31,32,31,30,30,30,29,29,30,31},
			{2012,31,31,31,32,31,31,29,30,30,29,30,30},
			{2013,31,31,32,31,31,31,30,29,30,29,30,30},
			{2014,31,31,32,32,31,30,30,29,30,29,30,30},
			{2015,31,32,31,32,31,30,30,30,29,29,30,31},
			{2016,31,31,31,32,31,31,29,30,30,29,30,30},
			{2017,31,31,32,31,31,31,30,29,30,29,30,30},
			{2018,31,32,31,32,31,30,30,29,30,29,30,30},
			{2019,31,32,31,32,31,30,30,30,29,30,29,31},
			{2020,31,31,31,32,31,31,30,29,30,29,30,30},
			{2021,31,31,32,31,31,31,30,29,30,29,30,30},
			{2022,31,32,31,32,31,30,30,30,29,29,30,30},
			{2023,31,32,31,32,31,30,30,30,29,30,29,31},
			{2024,31,31,31,32,31,31,30,29,30,29,30,30},
			{2025,31,31,32,31,31,31,30,29,30,29,30,30},
			{2026,31,32,31,32,31,30,30,30,29,29,30,31},
			{2027,30,32,31,32,31,30,30,30,29,30,29,31},
			{2028,31,31,32,31,31,31,30,29,30,29,30,30},
			{2029,31,31,32,31,32,30,30,29,30,29,30,30},
			{2030,31,32,31,32,31,30,30,30,29,29,30,31},
			{2031,30,32,31,32,31,30,30,30,29,30,29,31},
			{2032,31,31,32,31,31,31,30,29,30,29,30,30},
			{2033,31,31,32,32,31,30,30,29,30,29,30,30},
			{2034,31,32,31,32,31,30,30,30,29,29,30,31},
			{2035,30,32,31,32,31,31,29,30,30,29,29,31},
			{2036,31,31,32,31,31,31,30,29,30,29,30,30},
			{2037,31,31,32,32,31,30,30,29,30,29,30,30},
			{2038,31,32,31,32,31,30,30,30,29,29,30,31},
			{2039,31,31,31,32,31,31,29,30,30,29,30,30},
			{2040,31,31,32,31,31,31,30,29,30,29,30,30},
			{2041,31,31,32,32,31,30,30,29,30,29,30,30},
			{2042,31,32,31,32,31,30,30,30,29,29,30,31},
			{2043,31,31,31,32,31,31,29,30,30,29,30,30},
			{2044,31,31,32,31,31,31,30,29,30,29,30,30},
			{2045,31,32,31,32,31,30,30,29,30,29,30,30},
			{2046,31,32,31,32,31,30,30,30,29,29,30,31},
			{2047,31,31,31,32,31,31,30,29,30,29,30,30},
			{2048,31,31,32,31,31,31,30,29,30,29,30,30},
			{2049,31,32,31,32,31,30,30,30,29,29,30,30},
			{2050,31,32,31,32,31,30,30,30,29,30,29,31},
			{2051,31,31,31,32,31,31,30,29,30,29,30,30},
			{2052,31,31,32,31,31,31,30,29,30,29,30,30},
			{2053,31,32,31,32,31,30,30,30,29,29,30,30},
			{2054,31,32,31,32,31,30,30,30,29,30,29,31},
			{2055,31,31,32,31,31,31,30,29,30,29,30,30},
			{2056,31,31,32,31,32,30,30,29,30,29,30,30},
			{2057,31,32,31,32,31,30,30,30,29,29,30,31},
			{2058,30,32,31,32,31,30,30,30,29,30,29,31},
			{2059,31,31,32,31,31,31,30,29,30,29,30,30},
			{2060,31,31,32,32,31,30,30,29,30,29,30,30},
			{2061,31,32,31,32,31,30,30,30,29,29,30,31},
		    {2062,30,32,31,32,31,31,29,30,29,30,29,31},
			{2063,31,31,32,31,31,31,30,29,30,29,30,30},
			{2064,31,31,32,32,31,30,30,29,30,29,30,30},
			{2065,31,32,31,32,31,30,30,30,29,29,30,31},
			{2066,31,31,31,32,31,31,29,30,30,29,29,31},
			{2067,31,31,32,31,31,31,30,29,30,29,30,30},
			{2068,31,31,32,32,31,30,30,29,30,29,30,30},
			{2069,31,32,31,32,31,30,30,30,29,29,30,31},
			{2070,31,31,31,32,31,31,29,30,30,29,30,30},
			{2071,31,31,32,31,31,31,30,29,30,29,30,30},
			{2072,31,32,31,32,31,30,30,29,30,29,30,30},
			{2073,31,32,31,32,31,30,30,30,29,29,30,31},
			{2074,31,31,31,32,31,31,30,29,30,29,30,30},
			{2075,31,31,32,31,31,31,30,29,30,29,30,30},
			{2076,31,32,31,32,31,30,30,30,29,29,30,30},
			{2077,31,32,31,32,31,30,30,30,29,30,29,31},
			{2078,31,31,31,32,31,31,30,29,30,29,30,30},
			{2079,31,31,32,31,31,31,30,29,30,29,30,30},
			{2080,31,32,31,32,31,30,30,30,29,29,30,30},
			{2081,31,31,32,32,31,30,30,30,29,30,30,30},
			{2082,30,32,31,32,31,30,30,30,29,30,30,30},
			{2083,31,31,32,31,31,30,30,30,29,30,30,30},
			{2084,31,31,32,31,31,30,30,30,29,30,30,30},
			{2085,31,32,31,32,30,31,30,30,29,30,30,30},
			{2086,30,32,31,32,31,30,30,30,29,30,30,30},
			{2087,31,31,32,31,31,31,30,30,29,30,30,30},
			{2088,30,31,32,32,30,31,30,30,29,30,30,30},
			{2089,30,32,31,32,31,30,30,30,29,30,30,30},
			{2090,30,32,31,32,31,30,30,30,29,30,30,30},
    };

    private boolean isLeapYear(int year)
    {
        if (year%100==0){
            return (year%400==0);
        } else {
            return (year%4==0);
        }
    }

    private String getNepaliMonth(int m){
        switch(m){
            case 1:
                return "Baishak";
            case 2:
                return "Jestha";
            case 3:
                return "Ashad";
            case 4:
                return "Shrawn";
            case 5:
                return "Bhadra";
            case 6:
                return "Ashwin";
            case 7:
                return "Kartik";
            case 8:
                return "Mangshir";
            case 9:
                return "Poush";
            case 10:
                return "Magh";
            case 11:
                return "Falgun";
            case 12:
                return "Chaitra";
        }
        return null;
    }

    private String getDayOfWeek(int day){
        switch(day){
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
        }
        return null;
    }

    public int getTotalDaysInNepaliMonth(int year, int month){
        return bs[year - 2000 - 1][month];
    }

    public NepaliDate engToNep(int Year, int Month, int Day){

            // english month data.
            int [] months = {31,28,31,30,31,30,31,31,30,31,30,31};
            int [] lmonth = {31,29,31,30,31,30,31,31,30,31,30,31};

            int def_eyy = 1944;									//spear head english date...
            int def_nyy = 2000, def_nmm = 9, def_ndd = 17-1;		//spear head nepali date...
            int total_eDays=0, total_nDays, a, day=7-1;		//all the initializations...
            int m, y, numDay;

            // count total no. of days in-terms of year
            for(int i=0; i<(Year-def_eyy); i++){	//total days for month calculation...(english)
                if(isLeapYear(def_eyy+i))
                    for(int j=0; j<12; j++)
                        total_eDays += lmonth[j];
                else
                    for(int j=0; j<12; j++)
                        total_eDays += months[j];
            }

            // count total no. of days in-terms of month
            for(int i=0; i<(Month-1); i++){
                if(isLeapYear(Year))
                    total_eDays += lmonth[i];
                else
                    total_eDays += months[i];
            }

            // count total no. of days in-terms of date
            total_eDays += Day;

            int i = 0,j = def_nmm;
            total_nDays = def_ndd;
            m = def_nmm;
            y = def_nyy;

            // count nepali date from array
            while(total_eDays != 0) {
                a = bs[i][j];
                total_nDays++;						//count the days
                day++;								//count the days interms of 7 days
                if(total_nDays > a){
                    m++;
                    total_nDays=1;
                    j++;
                }
                if(day > 7)
                    day = 1;
                if(m > 12){
                    y++;
                    m = 1;
                }
                if(j > 12){
                    j = 1; i++;
                }
                total_eDays--;
            }
            numDay=day;
            return new NepaliDate(y, m, total_nDays, getDayOfWeek(day), getNepaliMonth(m), numDay);
        }

    public static class NepaliDate{
        int year,month,date,numDay;
        String nepaliMonth, day;

        NepaliDate(int year, int month, int date, String day, String nepaliMonth, int numDay) {
            this.year = year;
            this.month = month;
            this.date = date;
            this.day = day;
            this.nepaliMonth = nepaliMonth;
            this.numDay = numDay;
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDate() {
            return date;
        }

        public String getDay() {
            return day;
        }

        public String getNepaliMonth() {
            return nepaliMonth;
        }

        public int getNumDay() {
            return numDay;
        }

        public int getStartDay(){
            int engToNepDate = this.date;
            int startDay = this.numDay;

            int temp = numDay;
            while (engToNepDate !=1){
                engToNepDate --;
                if(startDay>1){
                    startDay --;
                }else if(startDay == 1){
                    startDay = 7;
                }
            }
            return startDay;
        }


        @Override
        public String toString() {
            return "NepaliDate{" +
                    "year=" + year +
                    ", month=" + month +
                    ", date=" + date +
                    ", numDay=" + numDay +
                    ", nepaliMonth='" + nepaliMonth + '\'' +
                    ", day='" + day + '\'' +
                    '}';
        }
    }

}
