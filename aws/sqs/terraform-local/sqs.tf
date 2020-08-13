resource "aws_sqs_queue" "dlq_test_queue_1" {
  name = "dlq_test_queue_1"
  receive_wait_time_seconds = 10
  message_retention_seconds = 1209600
}

resource "aws_sqs_queue" "test_queue_1" {
  name = "test_queue_1"
  receive_wait_time_seconds = 10
  visibility_timeout_seconds = 120
  redrive_policy = jsonencode({
    deadLetterTargetArn = aws_sqs_queue.dlq_test_queue_1.arn
    maxReceiveCount = 1
  })
}
