/* java.lang.SMath -- common mathematical functions
   Copyright (C) Sourav.*/


/*
 * Some of the algorithms in this class are in the public domain, as part
 * of fdlibm (freely-distributable math library), available at
 * http://www.netlib.org/fdlibm/, and carry the following copyright:
 * ====================================================
 * Copyright (C) 1993 by Sun Microsystems, Inc. All rights reserved.
 *
 * Developed at SunSoft, a Sun Microsystems, Inc. business.
 * Permission to use, copy, modify, and distribute this
 * software is freely granted, provided that this notice
 * is preserved.
 * ====================================================
 */
package swisseph;

/**
 * Helper class mapping all mathematical functions and constants to
 * the java.lang.Math class.
 */
public class SMath
		implements java.io.Serializable
		{
  public static final double E = Math.E;
  public static final double PI = Math.PI;
  public static int abs(int i) { return Math.abs(i); }
  public static long abs(long l) { return Math.abs(l); }
  public static float abs(float f) { return Math.abs(f); }
  public static double abs(double d) { return Math.abs(d); }
  public static int min(int a, int b) {return Math.min(a, b); }
  public static long min(long a, long b) {return Math.min(a, b); }
  public static float min(float a, float b) {return Math.min(a, b); }
  public static double min(double a, double b) {return Math.min(a, b); }
  public static int max(int a, int b) { return Math.max(a, b); }
  public static long max(long a, long b) { return Math.max(a, b); }
  public static float max(float a, float b) { return Math.max(a, b); }
  public static double max(double a, double b) { return Math.max(a, b); }
  public static double sin(double a) { return Math.sin(a); }
  public static double cos(double a) { return Math.cos(a); }
  public static double tan(double a) { return Math.tan(a); }
  public static double asin(double x) { return Math.asin(x); }
  public static double acos(double x) { return Math.acos(x); }
  public static double atan(double x) { return Math.atan(x); }
  public static double atan2(double y, double x) { return Math.atan2(y, x); }
  public static double exp(double x) { return Math.exp(x); }
  public static double log(double x) { return Math.log(x); }
  public static double sqrt(double x) { return Math.sqrt(x); }
  public static double pow(double x, double y) { return Math.pow(x, y); }
  public static double IEEEremainder(double x, double y) { return Math.IEEEremainder(x, y); }
  public static double ceil(double a) { return Math.ceil(a); }
  public static double floor(double a) { return Math.floor(a); }
  public static double rint(double a) { return Math.rint(a); }
  public static int round(float f) { return Math.round(f); }
  public static long round(double d) { return Math.round(d); }
  public static synchronized double random() { return Math.random(); }
//  public static double toRadians(double degrees) { return Math.toRadians(degrees); }
//  public static double toDegrees(double rads) { return Math.toDegrees(rads); }
//  public static double signum(double a)
//  public static float signum(float a)
//  public static double ulp(double d)
//  public static float ulp(float f)
}
