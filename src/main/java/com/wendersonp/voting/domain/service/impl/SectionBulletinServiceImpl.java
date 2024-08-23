package com.wendersonp.voting.domain.service.impl;

import com.wendersonp.voting.domain.model.SectionReportEntity;
import com.wendersonp.voting.domain.service.ISectionBulletinBuilderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SectionBulletinServiceImpl implements ISectionBulletinBuilderService {

    private static final int PADDING_SIZE = 40;
    @Override
    public String buildBulletin(SectionReportEntity sectionReportEntity) {
        StringBuilder bulletinBuilder = new StringBuilder();
        addDivider(bulletinBuilder);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        addLine(bulletinBuilder, "Data relat\u00F3rio", LocalDateTime.now().format(dateTimeFormatter));
        jumpLine(bulletinBuilder);

        addLine(bulletinBuilder, "Cargo:", sectionReportEntity.getSectionEntity().getRunningPosition().getName());
        jumpLine(bulletinBuilder);

        addLine(bulletinBuilder, "Candidatos", "Votos");
        jumpLine(bulletinBuilder);

        sectionReportEntity.getVoteCountMap().forEach((candidate, votes) ->
                addLine(bulletinBuilder, candidate.getName(), String.valueOf(votes))
        );
        jumpLine(bulletinBuilder);

        addLine(bulletinBuilder, "Total de votos", String.valueOf(sectionReportEntity.getTotalVotes()));
        jumpLine(bulletinBuilder);

        addLine(bulletinBuilder, "Vencedor:", sectionReportEntity.getWinner().getName());
        addDivider(bulletinBuilder);

        return bulletinBuilder.toString();
    }

    private void addDivider(StringBuilder builder) {
        builder.append("-".repeat(40));
    }

    private void jumpLine(StringBuilder stringBuilder) {
        stringBuilder.append("\n");
    }

    private void addLine(StringBuilder builder, String initialExpression, String endExpression) {
        int padding = PADDING_SIZE - endExpression.length();
        builder.append(("%-"+padding+"s%s\n").formatted(initialExpression, endExpression));
    }
}
