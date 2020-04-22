package com.javabyexamples.java.tools.lombok.annotations.sneakythrows;

public class SampleFacade {

    private SampleService sampleService = new SampleService();

    public void callThrowingUnchecked() {
        try {
            sampleService.throwsUncheckedAndSkips();
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }

    public void callSneakingThrowingChecked() {
        try {
            sampleService.sneakyThrowsCheckedAndSkips();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
    public void callSneakingThrowingChecked() {
        try {
            sampleService.sneakyThrowsCheckedAndSkips();
        } catch (IOException e) { // java: exception java.io.IOException is never thrown in body of corresponding try statement
            System.out.println(e);
        }
    }
   */
}
