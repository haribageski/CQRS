package command_module

import command_module.Aggregates.Aggregate.Identifier
import command_module.Aggregates.{Aggregate, PatientAggregate}

import scala.collection.immutable.HashMap

trait TrodisRepository extends StandardRepository { self: CommandBus.type =>

  protected override val aggregates: HashMap[Identifier, Aggregate[_]] =
    HashMap(PatientAggregate.aggregateId -> PatientAggregate)

  protected def loadAggregate(aggregateId: Identifier): Option[Aggregate[_]] = load(aggregateId)

}
