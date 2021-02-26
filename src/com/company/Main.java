package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    String[] workersanditems = scanner.nextLine().split(" ");
        String[] workertimeperitem = scanner.nextLine().split(" ");
        int workerscount = Integer.parseInt(workersanditems[0]);
        int itemscount = Integer.parseInt(workersanditems[1]);
        int[] workertimerequiredperitem = new int[workerscount];
        for(int i=0;i<workerscount;i++){
            workertimerequiredperitem[i]=Integer.parseInt(workertimeperitem[i]);
        }
        System.out.print(minTime(workerscount,itemscount,workertimerequiredperitem));
    }

    private static int minTime(int workerscount, int itemscount, int[] workertimerequiredperitem) {
        int minimumtime=0;
        Arrays.sort(workertimerequiredperitem);
        int[] workertimeremainingperitem = new int[workerscount];
        for (int k = 0; k < workerscount; k++) {
            workertimeremainingperitem[k] = workertimerequiredperitem[k];
        }
        if(workerscount>itemscount) return workertimerequiredperitem[workerscount-1];
        itemscount-=workerscount;
        while(itemscount>0){
            int timeforthiscycle = 0;
            for(int i=0;i<workerscount;i++){
                if(itemscount>0){
                    if(timeforthiscycle==0)timeforthiscycle = workertimeremainingperitem[i];
                    if(workertimeremainingperitem[i]>timeforthiscycle){
                        workertimeremainingperitem[i]-=timeforthiscycle;
                    }else {
                        workertimeremainingperitem[i] = workertimeremainingperitem[i] - timeforthiscycle;
                        itemscount--;
                        if(itemscount>0)workertimeremainingperitem[i] += workertimerequiredperitem[i];
                    }
                }else{
                    while(i<workerscount){
                        workertimeremainingperitem[i]-=timeforthiscycle;
                        i++;
                    }
                }
            }
            minimumtime+=timeforthiscycle;
        }

        int maxremaining=0;
        for(int j=0;j<workerscount;j++){
            maxremaining = Math.max(maxremaining,workertimeremainingperitem[j]);
        }
        minimumtime+=maxremaining;

        return minimumtime;
    }
}
