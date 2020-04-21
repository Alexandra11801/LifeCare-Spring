package ru.itis.lifecarespring.services;

import ru.itis.lifecarespring.dto.RevisionDto;
import ru.itis.lifecarespring.models.User;

import java.util.List;

public interface RevisionsService {
	void suggestRevision(RevisionDto dto, String articleTitle);
	List<RevisionDto> allRevisions(long userId);
	void accept(long revisionId);
	void reject(long revisionId);
}
