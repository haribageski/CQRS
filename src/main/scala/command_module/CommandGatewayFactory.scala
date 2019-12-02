package command_module

import command_module.Commands.Command

import scala.concurrent.Future
import scala.concurrent.duration.TimeUnit

trait CommandGateway {


  def sendCommand(command: Command): Future[Unit]


  def sendCommandAndWaitForAResult(command: Command, timeout: TimeUnit): Future[Response]

}
