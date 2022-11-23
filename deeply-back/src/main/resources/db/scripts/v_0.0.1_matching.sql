create table if not exists matching
(
    id            int(11)  not null AUTO_INCREMENT,
    user_id    int(11),
    opponent_user_id     int(11),
    success_yn char(1) default 'N' comment '매칭 성공 유무',
    create_dt datetime,
    create_id int(11),
    update_dt datetime,
    update_id int(11),
    primary key (id)
) engine = MyISAM;

