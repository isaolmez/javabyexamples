package com.javabyexamples.java.core.process;

import java.lang.ProcessBuilder.Redirect;
import java.lang.ProcessHandle.Info;

public class ProcessOperations {

  public static void main(String[] args) throws Exception {
    final ProcessOperations processOperations = new ProcessOperations();
    processOperations.start();
    processOperations.startAndGetPid();
    processOperations.startAndGetExitStatus();
    processOperations.startAndGetOutput();
    processOperations.startAndGetOutput2();

    processOperations.startAndGetProcessInfo();
    processOperations.startThenDestroy();
    processOperations.startThenDestroyForcibly();
  }

  public void start() throws Exception {
    final Process process = new ProcessBuilder().command("echo", "hello world!").start();
  }

  public void startAndGetPid() throws Exception {
    final Process process = new ProcessBuilder().command("echo", "hello world!").start();
    System.out.println("Process id: " + process.pid());
  }

  public void startAndGetExitStatus() throws Exception {
    final Process process = new ProcessBuilder().command("echo", "hello world!").start();
    System.out.println("Process id: " + process.pid());
    process.waitFor();
    System.out.println("Exit value: " + process.exitValue());
  }

  public void startAndGetOutput() throws Exception {
    final Process process = new ProcessBuilder()
        .command("echo", "hello world!")
        .inheritIO()
        .start();
    System.out.println("Process id: " + process.pid());
    process.waitFor();
    System.out.println("Exit value: " + process.exitValue());
  }

  public void startAndGetOutput2() throws Exception {
    final Process process = new ProcessBuilder()
        .command("echo", "hello world!")
        .redirectOutput(Redirect.INHERIT)
        .start();
    System.out.println("Process id: " + process.pid());
    process.waitFor();
    System.out.println("Exit value: " + process.exitValue());
  }

  public void startAndGetProcessInfo() throws Exception {
    final Process process = new ProcessBuilder()
        .command("sleep", "2")
        .redirectOutput(Redirect.INHERIT)
        .start();

    final Info info = process.info();
    System.out.println("Process info: " + info);
  }

  public void startThenDestroy() throws Exception {
    final Process process = new ProcessBuilder()
        .command("sleep", "2")
        .redirectOutput(Redirect.INHERIT)
        .start();

    final ProcessHandle processHandle = process.toHandle();
    processHandle.destroy();
    process.waitFor();
  }

  public void startThenDestroyForcibly() throws Exception {
    final Process process = new ProcessBuilder()
        .command("sleep", "2")
        .redirectOutput(Redirect.INHERIT)
        .start();

    final ProcessHandle processHandle = process.toHandle();
    processHandle.destroyForcibly();
    process.waitFor();
  }
}
