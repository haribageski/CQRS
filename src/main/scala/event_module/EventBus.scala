package event_module

import message_module.PatientUpdated

object EventBus {

  val eventListeners: List[EventListener] = List(
    new EventListener {
      override def handle(event: Event): Unit = event match {
        case de: DomainEvent[_] => de match {
          case PatientUpdated(aggregateId) =>
            println("Event listener detected event and did something")
          case _ => ()
        }
      }
    }
  )

  def dispatchEvent(event: Event): Unit = {
    val x = println("Event message:" + event + " dispatched to EventBus")
    eventListeners.foreach(_.handle(event))
  }
}
