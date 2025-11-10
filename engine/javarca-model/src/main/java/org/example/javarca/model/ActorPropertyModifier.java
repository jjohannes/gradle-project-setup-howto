package org.example.javarca.model;

/**
 * Represents the modification of a {@link ActorProperty} to a certain value.
 */
public record ActorPropertyModifier(ActorProperty p, int value) {}
