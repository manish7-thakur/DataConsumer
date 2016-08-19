package com.glassbeam.domain

case class DataElement(id: String, channelNumber: String) {
  def getChannel = if (channelNumber.contains("1")) 1 else 2
}
