package com.example

import ecs._
import ecs.components._
import ecs.systems._
import ecs.events._

object Hello {
  val entityManager = new EntityManager()
  val componentManager = new ComponentManager()
  val systemManager = new SystemManager()
  val eventManager = new EventManager(componentManager, systemManager)
  systemManager.init(eventManager)

  val player1 = entityManager.generateEntity()
  val creature1 = entityManager.generateEntity()
  val creature2 = entityManager.generateEntity()

  val c1AP = componentManager.createComponent[HasAttackPower](creature1, new HasAttackPower(4))
  val c1HP = componentManager.createComponent[HasHP](creature1, new HasHP(5))

  val c2AP = componentManager.createComponent[HasAttackPower](creature2, new HasAttackPower(2))
  val c2HP = componentManager.createComponent[HasHP](creature2, new HasHP(2))

  val p1HP = componentManager.createComponent[HasHP](player1, new HasHP(30))

  val hps = systemManager.createSystem[HPSystem]()

  def main(args: Array[String]): Unit = {
    printGameStatus()
    eventManager.enqueueNewEvent(new Attack(creature1, creature2))
    doGameLogic()

    printGameStatus()

    eventManager.enqueueNewEvent(new Attack(creature1, player1))
    doGameLogic()

   printGameStatus()
  }

  def printGameStatus(): Unit = {
    println("Player 1: " + p1HP.currHP)
    println("Creature 1: " + c1AP.currAP + "/" + c1HP.currHP)
    println("Creature 2: " + c2AP.currAP + "/" + c2HP.currHP)
  }

  def doGameLogic(): Unit = {
    eventManager.execute()
    systemManager.processAll()
    eventManager.dequeueEvent()

    if (eventManager.hasEvent)
      doGameLogic()
  }
}

//old ECS debugging code ver 1.0

    // val ent: Entity = entityManager.generateEntity()
    // println("generated entity")

    // val apComp = componentManager.createComponent[HasAttackPower](ent, new HasAttackPower(5))
    // val hpComp = componentManager.createComponent[HasHP](ent, new HasHP(30))
    // println("created two components")

    // val ads = systemManager.createSystem[AttackDamageSystem](new AttackDamageSystem())
    // println("created new attack damage system")

    // val node = new AttackDamageSystemNode(hpComp, apComp)
    // println("created new attack damage system node")

    // ads.addNode(ent, node)
    // println("added node")


    // println("##### NODE PROCESSING #####")
    // systemManager.processAll()
    // println("##### PROCESSING DONE #####")

    // println("Game's HP Component: " + hpComp.hp)

    // systemManager.processAll()

    // val typeComp = componentManager.createComponent[HasType](
    //   ent, new HasType(TypeBitMaskHelper.aura | TypeBitMaskHelper.drive | TypeBitMaskHelper.soul))

    // println(TypeBitMaskHelper.getAsString(typeComp.typ))
