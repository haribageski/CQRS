package command_module

import command_module.Aggregates.Aggregate.Identifier
import command_module.Commands.{Command, InsertPatientCommand}
import event_module.{DomainEvent, EventBus, EventHandler}
import message_module.PatientUpdated

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Aggregates {

  sealed trait Aggregate[CommandType <: Command] { self =>

    type AggregateType = CommandType
    type Entity
    val aggregateId: Identifier

    protected val commandHandler: CommandHandler[CommandType]

//    val eventHandler: EventHandler

    def handle(command: CommandType): Unit = {

      val domainEvent: Future[DomainEvent[_]] = commandHandler.handle(command)
      val sentToEventBus: Future[Unit] = domainEvent.map((event: DomainEvent[_]) => {
        println("DomainEvent produced:" + event)
        EventBus.dispatchEvent(event)
      })
      Thread.sleep(500)
    }

  }

  object Aggregate {
    type Identifier = String
  }


  object PatientAggregate extends Aggregate[InsertPatientCommand] { self =>

    override type Entity = PatientEntity

    override val aggregateId: Identifier = "Id for PatientAggregate"

//    override val eventHandler = ???

    override protected val commandHandler = new CommandHandler[InsertPatientCommand] {
      override def handle(command: InsertPatientCommand): Future[PatientUpdated] = {
        update(command.entity)
        println("Command " + InsertPatientCommand + " handled" )
        Future(PatientUpdated(self.aggregateId))
      }
    }

    protected def update(entity: PatientEntity): Future[Unit] = {
      println(s"PatientEntity: $entity updated")
      Future(())
    }
  }

}
