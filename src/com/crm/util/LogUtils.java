/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author hungdt
 */
public class LogUtils {
    public static void printLog(String action, String msg)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(sdf.format(cal.getTime()) +  ": ["+action+"] " + msg);
    }
}
