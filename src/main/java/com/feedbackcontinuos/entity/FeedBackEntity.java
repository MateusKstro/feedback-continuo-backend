package com.feedbackcontinuos.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "feedback")
public class FeedBackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FEEDBACK_SEQ")
    @SequenceGenerator(name = "FEEDBACK_SEQ", sequenceName = "seq_feedback", allocationSize = 1)
    @Column(name = "id_feedback")
    private Integer idUser;

    @Column(name = "message")
    private String message;

    @Column(name = "date_create")
    private LocalDateTime dataEHora;

    @Column(name = "anonymous")
    private Boolean anonymous;

    @Column(name = "id_user", insertable = false, updatable = false)
    private String userId;

    @Column(name = "feedback_id_user", insertable = false, updatable = false)
    private String feedbackUserId;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UsersEntity feedbackEntityGiven;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_id_user")
    private UsersEntity feedbackEntityReceived;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "feedback_tags",
            joinColumns = @JoinColumn(name = "id_feedback"),
            inverseJoinColumns = @JoinColumn(name = "id_tag"))
    private Set<TagEntity> tagEntities;
}
