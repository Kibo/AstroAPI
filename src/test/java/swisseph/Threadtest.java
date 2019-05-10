package swisseph;

public class Threadtest {

        public static void main(String[] p) {
                int threadcount = 100;
                if (p.length == 1 && Integer.parseInt(p[0]) > 0) {
                        threadcount = Integer.parseInt(p[0]);
                }

                for(int t = threadcount; t > 0; t--) {
                        SEThread se = new SEThread(t);
                        se.start();
                }
        }
}

class SEThread extends Thread {
        static final int MAX_COUNT = 100;

        int threadNo;
        int[] planets = new int[]{ SweConst.SE_SUN,
                        SweConst.SE_MOON,
                        SweConst.SE_MARS,
                        SweConst.SE_MERCURY,
                        SweConst.SE_JUPITER,
                        SweConst.SE_VENUS,
                        SweConst.SE_SATURN,
                        SweConst.SE_TRUE_NODE };


        public SEThread(int n) {
                threadNo = n;
        }

        public void run() {
                System.err.println("Thread " + threadNo + " started");

                SwissEph sw = new SwissEph("/data/ephemeris");
                //int iflag = SweConst.SEFLG_SWIEPH;
                int iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_SPEED;
                double[] xx = new double[6];
                StringBuffer serr = new StringBuffer();

                int cnt = 0;
                while(cnt < MAX_COUNT) {
                        cnt++;

                        // Random julian day between jan. 1, 1800 and dec. 31, 2399 to restrict
                        // calculation to the se*_18.se1 data files
                        double randomJD = Math.random() * (2597640.5 - 2378496.5) + 2378496.5;
                        int randomPlanet = planets[ new java.util.Random().nextInt(planets.length) ];

                        sw.swe_calc(
                                        randomJD,
                                        randomPlanet,
                                        iflag,
                                        xx,
                                        serr
                        );
                 System.out.println(xx[0]);       
                }
        }
}
