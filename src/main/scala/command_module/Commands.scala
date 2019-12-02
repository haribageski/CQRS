package command_module

import command_module.Aggregates.PatientAggregate

object Commands {
  import CommandBus._

  /**
    * Commands always have exactly one destination.
    */
  sealed trait Command {

    val entity: Entity
    val targetAggregateIdentifier: String

    def run(): Unit = dispatch(this)
  }

  case class InsertPatientCommand(entity: PatientEntity) extends Command {
    override val targetAggregateIdentifier: String = PatientAggregate.aggregateId
  }

}