package com.javabyexamples.aws.sqs.jms.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestMessage {

  private String name;
  private String uuid;
}
