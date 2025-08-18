package com.example.golbackend.modules.championship_managment.exception;

public class ChampionshipNotFoundException extends RuntimeException {
  public ChampionshipNotFoundException(Long id) {
    super("Championship " + id + " not found");
  }

}
