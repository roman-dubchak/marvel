package ru.stm_labs.marvel.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table (name ="image_character")
public class ImageCharacter extends BaseEntity{

}
