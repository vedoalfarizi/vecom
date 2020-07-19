package com.example.vecom.exception;

import java.util.Date;

public class ErrorResponse {
  private Date timestamp;
  private String status;
  private String message;

  public ErrorResponse(Date timestamp, String status, String message) {
    this.timestamp = timestamp;
    this.status = status;
    this.message = message;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}