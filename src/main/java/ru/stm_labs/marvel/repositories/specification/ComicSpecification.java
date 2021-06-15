package ru.stm_labs.marvel.repositories.specification;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import ru.stm_labs.marvel.entities.QComic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComicSpecification {
    private static final QComic Q_Comic = QComic.comic;

    public static BooleanExpression createPredicate(
            Long idCharacter,
            String title,
            String description,
            String format,
            Integer pageCount,
            String text,
            String resourceUri,
            Double price,
            LocalDateTime modified
    ) {

        List<BooleanExpression> booleanExpressions = new ArrayList<>();

        if (!Objects.equals(idCharacter, null)) {
            booleanExpressions.add(Q_Comic.characters.any().id.eq(idCharacter));
        }

        if (!Objects.equals(modified, null)) {
            booleanExpressions.add(Q_Comic.modified.before(modified));
        }

        if (!Objects.equals(title, null)) {
            title = title.replaceAll("\\s{2,}", " ").trim();
            booleanExpressions.add(Q_Comic.title.likeIgnoreCase("%" + title.trim() + "%"));
        }

        if (!Objects.equals(description, null)) {
            description = description.replaceAll("\\s{2,}", " ").trim();
            booleanExpressions.add(Q_Comic.description.likeIgnoreCase("%" + description.trim() + "%"));
        }

        if (!Objects.equals(pageCount, null)) {
            booleanExpressions.add(Q_Comic.pageCount.eq(pageCount));
        }

        if (!Objects.equals(description, null)) {
            description = description.replaceAll("\\s{2,}", " ").trim();
            booleanExpressions.add(Q_Comic.description.likeIgnoreCase("%" + description.trim() + "%"));
        }

        if (!Objects.equals(text, null)) {
            text = text.replaceAll("\\s{2,}", " ").trim();
            booleanExpressions.add(Q_Comic.text.likeIgnoreCase("%" + format.trim() + "%"));
        }

        if (!Objects.equals(resourceUri, null)) {
            resourceUri = resourceUri.replaceAll("\\s{2,}", " ").trim();
            booleanExpressions.add(Q_Comic.resourceUri.likeIgnoreCase("%" + resourceUri.trim() + "%"));
        }
        if (!Objects.equals(price, null)) {
            booleanExpressions.add(Q_Comic.prices.any().price.eq(price));
        }

        BooleanExpression resultBooleanExpression = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression b : booleanExpressions) {
            resultBooleanExpression = resultBooleanExpression.and(b);
        }

        return resultBooleanExpression;
    }
}
