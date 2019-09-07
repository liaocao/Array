public class Main {

    public static void main(String[] args) {

//        int[] arr = new int[10];
//        for(int i = 0; i < arr.length; i++){
//            arr[i] = i;
//        }
//
//        int[] scores = new int[]{100, 99, 66};
//
//        for (int i = 0; i < scores.length; i++){
//            System.out.println(scores[i]);
//        }

        Array arr = new Array(20);
        for(int i = 0; i < 10; i++){
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);
    }
}
