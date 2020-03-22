package ru.itis.lifecarespring.services;

import ru.itis.lifecarespring.dto.AddedCommentDto;

public interface CommentsService {
	void addComment(AddedCommentDto form, String senderName);
}
