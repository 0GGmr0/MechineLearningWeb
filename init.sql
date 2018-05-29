-- auto-generated definition
create table day_task
(
  user_id     int                                 not null
  comment '用户id
'
    primary key,
  time        varchar(16)                         null
  comment '事件发生的时间',
  content     text                                null
  comment '事件内容',
  create_time timestamp default CURRENT_TIMESTAMP not null
  comment '每个小任务创建的时间'
);

-- auto-generated definition
create table navigation
(
  sequence    int                                 not null
  comment '每一个导航出现的顺序'
    primary key,
  id          int                                 null
  comment '发布者id，对应user',
  title       varchar(128)                        null
  comment '导航标题',
  content     longtext                            null
  comment '导航内容',
  create_time timestamp default CURRENT_TIMESTAMP not null
  comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null
  comment '更新时间'
)
  comment '导航，按照时间顺序出现，可人为设置顺序置顶'
  charset = utf8;

-- auto-generated definition
create table user
(
  user_id         int                                 not null
  comment '工号'
    primary key,
  user_name       varchar(32)                         null
  comment '用户名',
  pass_word       varchar(128)                        null
  comment '密码',
  rool            int                                 null
  comment '用户的身份 1学生 2老师',
  phone           varchar(16)                         null
  comment '用户电话',
  avatar          varchar(256)                        null
  comment '用户头像',
  department      varchar(32)                         null
  comment '所属学院',
  introduction    text                                null
  comment '用户简介',
  register_time   timestamp default CURRENT_TIMESTAMP not null
  comment '用户注册时间',
  last_login_time timestamp default CURRENT_TIMESTAMP not null
  comment '上次登录时间',
  login_times     int default '0'                     null
  comment '登录次数',
  school          varchar(16)                         null
  comment '学校',
  background      int                                 null
  comment '1 本科生， 2 硕士生， 3 博士生',
  admin           int default '2'                     null
  comment '1是管理员 2不是管理员 默认是2'
)
  comment '用户表'
  charset = utf8;


-- auto-generated definition
-- auto-generated definition
create table notice
(
  notice_id   int auto_increment
  comment '发布者id，对应user'
    primary key,
  user_id     int                                 null,
  content     text                                null
  comment '通知内容',
  create_time timestamp default CURRENT_TIMESTAMP not null
  comment '创建时间',
  type        varchar(16)                         null
  comment '公告种类meeting,outing,competition',
  event_time  timestamp default CURRENT_TIMESTAMP null
  comment '每个事件的开始时间',
  title       text                                null
  comment '通知标题'
)
  comment '通知，出现按照时间顺序'
  charset = utf8;


-- auto-generated definition
create table reply
(
  reply_id       int auto_increment
  comment '每个topic回复的id'
    primary key,
  user_id        int                                 null
  comment '发布者id，对应user',
  content        longtext                            null
  comment '回复内容',
  create_time    timestamp default CURRENT_TIMESTAMP not null
  comment '创建时间',
  update_time    timestamp default CURRENT_TIMESTAMP not null
  comment '更新时间',
  topic_id       int                                 null
  comment 'Topic的Id',
  reply_like_num int                                 null
  comment '每条回复被点赞的次数'
)
  comment '话题回复，按照时间顺序出现'
  charset = utf8;

-- auto-generated definition
create table reply_msg
(
  reply_msg_id int auto_increment
    primary key,
  reply_id     int             not null,
  like_user_id int             null,
  likeed       int default '2' null
  comment '1喜欢 2不喜欢 默认是2'
);



-- auto-generated definition
create table topic
(
  topic_id          int auto_increment
  comment '每一个话题出现的顺序'
    primary key,
  user_id           int                                 null
  comment '发布者id，对应user',
  title             varchar(128)                        null
  comment '话题标题',
  content           longtext                            null
  comment '话题内容',
  theme             varchar(32)                         null
  comment '话题的主题',
  create_time       timestamp default CURRENT_TIMESTAMP not null
  comment '创建时间',
  update_time       timestamp default CURRENT_TIMESTAMP not null
  comment '更新时间',
  topic_comment_num int default '0'                     null
  comment '话题的评论数',
  topic_like_num    int default '0'                     null
  comment '文章点赞数'
)
  comment '话题，按照时间顺序出现，可人为设置顺序置顶'
  charset = utf8;

-- auto-generated definition
create table topic_msg
(
  topic_msg_id  int auto_increment
  comment '只是作为主键使用'
    primary key,
  topic_id      int             not null
  comment '话题id',
  reply_user_id int             null
  comment '回复者id',
  liked         int default '2' null
  comment '回复者是否给话题点赞 1点赞 2没有 默认是2',
  commented     int default '2' null
  comment '回复者是否给话题评论 1评论 2没有评论'
);

-- auto-generated definition
create table user
(
  user_id         int                                 not null
  comment '工号'
    primary key,
  user_name       varchar(32)                         null
  comment '用户名',
  pass_word       varchar(128)                        null
  comment '密码',
  rool            int                                 null
  comment '用户的身份 1学生 2老师 3管理员',
  phone           varchar(16)                         null
  comment '用户电话',
  avatar          varchar(256)                        null
  comment '用户头像',
  department      varchar(32)                         null
  comment '所属学院',
  introduction    text                                null
  comment '用户简介',
  register_time   timestamp default CURRENT_TIMESTAMP not null
  comment '用户注册时间',
  last_login_time timestamp default CURRENT_TIMESTAMP not null
  comment '上次登录时间',
  login_times     int default '0'                     null
  comment '登录次数',
  school          varchar(16)                         null
  comment '学校',
  background      int                                 null
  comment '1 本科生， 2 硕士生， 3 博士生'
)
  comment '用户表'
  charset = utf8;

