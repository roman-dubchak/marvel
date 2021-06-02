package ru.stm_labs.marvel.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "comic")
public class Comic extends BaseEntity {


}
