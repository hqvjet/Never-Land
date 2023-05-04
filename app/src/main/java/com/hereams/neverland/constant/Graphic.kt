package com.hereams.neverland.constant

val SPRITES_SIZE = 64 * 8

val DIRECTION_FORWARD = 0
val DIRECTION_RIGHT = 1
val DIRECTION_DOWN = 2
val DIRECTION_LEFT = 3

val MOVE_SPRITE = "Move sprites"
val ATTACK_SPRITE = "Attack sprites"

val ACTION_ATTACK = "ATTACK"
val ACTION_MOVE = "MOVE"

val MAX_MOVEMENT_SPRITES_SIZE_OF_CHARACTER = 6
val MAX_MOVEMENT_SPRITES_SIZE_OF_ENEMY = 6
val MAX_MOVEMENT_SPRITES_SIZE_OF_TILEMAP = 12

val MAX_ATTACK_SPRITES_SIZE_OF_CHARACTER = 9
val MAX_ATTACK_SPRITES_SIZE_OF_ENEMY = 9

val CIRCLE_RADIUS = 64f + 16f

val MAX_FPS: Double = 60.0
val MAX_UPS:Double = 60.0
val UPS_PERIOD: Double = 1000 / MAX_UPS

val TILE_MOVEABLE = "MOVEABLE"
val TILE_IMMOVEABLE = "IMMOVEABLE"