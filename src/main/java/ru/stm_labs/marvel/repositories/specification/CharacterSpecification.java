package ru.stm_labs.marvel.repositories.specification;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import ru.stm_labs.marvel.entities.QCharacter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterSpecification {
    private static final QCharacter Q_CHARACTER = QCharacter.character;

    public static BooleanExpression createPredicate(
            Long idComic,
            String name,
            String description,
            String resourceUri,
            LocalDateTime modified
    ) {

        List<BooleanExpression> booleanExpressions = new ArrayList<>();

        if (!Objects.equals(idComic, null)) {
            booleanExpressions.add(Q_CHARACTER.comics.any().id.eq(idComic));
        }

        if (!Objects.equals(modified, null)) {
            booleanExpressions.add(Q_CHARACTER.modified.before(modified));
        }

        if (!Objects.equals(name, null)) {
            name = name.replaceAll("\\s{2,}", " ").trim();
            booleanExpressions.add(Q_CHARACTER.name.likeIgnoreCase("%" + name.trim() + "%"));
        }

        if (!Objects.equals(description, null)) {
            description = description.replaceAll("\\s{2,}", " ").trim();
            booleanExpressions.add(Q_CHARACTER.description.likeIgnoreCase("%" + description.trim() + "%"));
        }

        if (!Objects.equals(resourceUri, null)) {
            resourceUri = resourceUri.replaceAll("\\s{2,}", " ").trim();
            booleanExpressions.add(Q_CHARACTER.resourceUri.likeIgnoreCase("%" + resourceUri.trim() + "%"));
        }

        BooleanExpression resultBooleanExpression = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression b : booleanExpressions) {
            resultBooleanExpression = resultBooleanExpression.and(b);
        }

        return resultBooleanExpression;
    }
}
