package command_module

import command_module.Commands.Command
import event_module.DomainEvent

import scala.concurrent.Future

trait CommandHandler[CommandType <: Command] {

  def handle(command: CommandType): Future[DomainEvent[_]]    // basically it executes the action(s) that are part of the command

}
