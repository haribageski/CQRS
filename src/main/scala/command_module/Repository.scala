package command_module

import command_module.Aggregates.Aggregate
import command_module.Aggregates.Aggregate.Identifier

import scala.collection.immutable.HashMap

/**
  *  The repositories only need to be able to find aggregates based on their unique identifier.
  */
sealed trait Repository {
  protected val aggregates: HashMap[Identifier, Aggregate[_]]    // the key is the aggregate's id

  protected def load(identifier: Identifier): Option[Aggregate[_]] = aggregates.get(identifier)
}

/**
  * Used for saving (updating) the current state of an aggregate.
  */
trait StandardRepository extends Repository

/**
  * Used for storing the events of an aggregate.
  */
trait EventSourcingRepository extends Repository
