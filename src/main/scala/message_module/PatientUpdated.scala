package message_module

import command_module.Aggregates.Aggregate
import command_module.Commands.InsertPatientCommand
import event_module.DomainEvent

case class PatientUpdated(aggregateId: Aggregate.Identifier) extends DomainEvent[InsertPatientCommand](aggregateId)
