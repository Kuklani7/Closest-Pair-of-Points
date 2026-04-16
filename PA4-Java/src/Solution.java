import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static final double EPSILON=0.0001;
    
    private Integer _n_points;          // Number of points in the plane
    private ArrayList<Point> _points;  // This ArrayList contains all points in the plane
    
    public Solution (Integer n_points, ArrayList<Point> points){
        _n_points = n_points;
        _points = points;
    }
    private static boolean compare_points(Point a, Point b){
        if(a._x>b._x){
            return false;
        }
        if(b._x>a._x){
            return true;
        }
        if(a._y>b._y){
            return false;
        }
        return true;
    }
    private static boolean compare_points_y_termed(Point a, Point b){
        if(a._y>b._y){
            return false;
        }
        if(b._y>a._y){
            return true;
        }
        if(a._x>b._x){
            return false;
        }
        return true;
    }

    private static int index_after_insert(Point[] refer_array, int low, int high){
        int mid=(low+high)/2;
        Point temper=refer_array[mid];
        refer_array[mid]=refer_array[high];
        refer_array[high]=temper;
        int lower=low-1;
        Point last_point=refer_array[high];
        for(int j=low;j<high;j++){
            boolean swap=compare_points(refer_array[j],last_point);
            if(swap){
                lower++;
                Point temp=refer_array[j];
                refer_array[j]=refer_array[lower];
                refer_array[lower]=temp;
            }
        }
        Point temp=refer_array[lower+1];
        refer_array[lower+1]=refer_array[high];
        refer_array[high]=temp;
        return lower+1;
    }
    private static int index_after_insert_y_termed(Point[] refer_array, int low, int high){
        int mid=(low+high)/2;
        Point temper=refer_array[mid];
        refer_array[mid]=refer_array[high];
        refer_array[high]=temper;
        int lower=low-1;
        Point last_point=refer_array[high];
        for(int j=low;j<high;j++){
            boolean swap=compare_points_y_termed(refer_array[j],last_point);
            if(swap){
                lower++;
                Point temp=refer_array[j];
                refer_array[j]=refer_array[lower];
                refer_array[lower]=temp;
            }
        }
        Point temp=refer_array[lower+1];
        refer_array[lower+1]=refer_array[high];
        refer_array[high]=temp;
        return lower+1;
    }
     private static void quickSort(Point [] refer_array, int low, int high){
        if(low<high){
            int pivot=index_after_insert(refer_array,low,high);
            quickSort(refer_array,low,pivot-1);
            quickSort(refer_array,pivot+1,high);
        }
    }
    private static void quickSort_y_termed(Point [] refer_array, int low, int high){
        if(low<high){
            int pivot=index_after_insert_y_termed(refer_array,low,high);
            quickSort_y_termed(refer_array,low,pivot-1);
            quickSort_y_termed(refer_array,pivot+1,high);
        }
    }

    private static double distance_calc(Point a,Point b){
        double x_dist=a._x- b._x;
        double y_dist=a._y-b._y;
        return Math.sqrt((x_dist*x_dist)+(y_dist*y_dist));
    }
    private static double base_caser(Point [] copied){
        double min_dist=Double.MAX_VALUE;
        for(int i=0;i< copied.length-1;i++){
            for(int j=i+1;j<copied.length;j++){
                double store= distance_calc(copied[i],copied[j]);
                if(store<min_dist){
                    min_dist=store;
                }
            }
        }
        return min_dist;
    }
    private static double closer(Point [] refer_array, int low, int high){
        double distance=-1;
        if(high-low<=3){
            Point [] copied=new Point[(high-low)+1];
            int insert=0;
            for(int i=low;i<=high;i++){
                copied[insert]=refer_array[i];
                insert++;
            }
            distance=base_caser(copied);
            return distance;
        }
        int mid_way=(low+high)/2;
        double min_left=closer(refer_array,low,mid_way);
        double min_right=closer(refer_array,mid_way+1,high);
        double sides_min=Math.min(min_left,min_right);
        double x_mid=refer_array[mid_way]._x;
        ArrayList<Point> split_candidates=new ArrayList<>();
        for(int i=low;i<=high;i++){
            if(Math.abs(refer_array[i]._x-x_mid)<sides_min){
                split_candidates.add(refer_array[i]);
            }
        }
        Point [] array_for_quicky=new Point[split_candidates.size()];
        int into=0;
        for(Point p:split_candidates){
            array_for_quicky[into]=p;
            into++;
        }
        quickSort_y_termed(array_for_quicky,0, split_candidates.size()-1);
        double minimum=Double.MAX_VALUE;
        int max_index=split_candidates.size()-1;
        for(int i=0;i< split_candidates.size()-1;i++){
            boolean heil=false;
            if(i+8<=max_index){
                heil=true;
            }
            if(heil){
                for(int j=i+1;j<i+9;j++){
                    double dis=distance_calc(array_for_quicky[i], array_for_quicky[j]);
                    if(dis<minimum){
                        minimum=dis;
                    }
                }
            }
            else{
                for(int j=i+1;j< array_for_quicky.length;j++){
                    double dis=distance_calc(array_for_quicky[i], array_for_quicky[j]);
                    if(dis<minimum){
                        minimum=dis;
                    }
                }
            }
        }
        if(minimum<sides_min){
            sides_min=minimum;
        }
        return sides_min;
    }
    
    public double outputClosestDistance(){
        Point [] copied_into_array=new Point[_points.size()];
        for(int i=0;i<_points.size();i++){
            copied_into_array[i]=_points.get(i);
        }
        quickSort(copied_into_array,0,_points.size()-1);
        double ret_val=closer(copied_into_array,0,_points.size()-1);
        return ret_val;
    }

}
