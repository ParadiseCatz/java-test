public class MultiThread {

  public static void main(String[] args){
    System.out.println(Thread.currentThread().getName());
    for(int i=0; i<1000000; i++){ // Create a tons of'em
      new Thread("" + i){
        public void run(){
          int[] x = new int[1000];
          System.out.println("Thread: " + getName() + " running");
          while (true) {
            
          }
        }
      }.start();
    }
  }
}