package command_module

import command_module.Aggregates.PatientAggregate
import command_module.Commands.{Command, InsertPatientCommand}
import command_module.Commands

import scala.concurrent.Future
import scala.concurrent.duration.TimeUnit

// Currently it is more of a Command Dispatcher
object CommandBus extends TrodisRepository { self =>

  // fire and forget
  def dispatch(command: Command): Unit = command match {
    case insertPatientCmd: InsertPatientCommand =>
      println("Command:" + insertPatientCmd + " arrived at the CommandBus")
      loadAggregate(command.targetAggregateIdentifier)
        .foreach {
          case PatientAggregate =>
            println("PatientAggregate loaded")
            PatientAggregate.handle(insertPatientCmd)
        }
      ()
  }

  // fire and wait
  def dispatch(command: Command, timeout: TimeUnit): Future[Response] =
    ???

}
