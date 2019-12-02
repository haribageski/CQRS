package event_module

import command_module.Aggregates.Aggregate


sealed trait Event

class DomainEvent[AggregateType](aggregateId: Aggregate.Identifier) extends Event
