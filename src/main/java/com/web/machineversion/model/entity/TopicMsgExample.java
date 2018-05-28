package com.web.machineversion.model.entity;

import java.util.ArrayList;
import java.util.List;

public class TopicMsgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TopicMsgExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTopicMsgIdIsNull() {
            addCriterion("topic_msg_id is null");
            return (Criteria) this;
        }

        public Criteria andTopicMsgIdIsNotNull() {
            addCriterion("topic_msg_id is not null");
            return (Criteria) this;
        }

        public Criteria andTopicMsgIdEqualTo(Integer value) {
            addCriterion("topic_msg_id =", value, "topicMsgId");
            return (Criteria) this;
        }

        public Criteria andTopicMsgIdNotEqualTo(Integer value) {
            addCriterion("topic_msg_id <>", value, "topicMsgId");
            return (Criteria) this;
        }

        public Criteria andTopicMsgIdGreaterThan(Integer value) {
            addCriterion("topic_msg_id >", value, "topicMsgId");
            return (Criteria) this;
        }

        public Criteria andTopicMsgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_msg_id >=", value, "topicMsgId");
            return (Criteria) this;
        }

        public Criteria andTopicMsgIdLessThan(Integer value) {
            addCriterion("topic_msg_id <", value, "topicMsgId");
            return (Criteria) this;
        }

        public Criteria andTopicMsgIdLessThanOrEqualTo(Integer value) {
            addCriterion("topic_msg_id <=", value, "topicMsgId");
            return (Criteria) this;
        }

        public Criteria andTopicMsgIdIn(List<Integer> values) {
            addCriterion("topic_msg_id in", values, "topicMsgId");
            return (Criteria) this;
        }

        public Criteria andTopicMsgIdNotIn(List<Integer> values) {
            addCriterion("topic_msg_id not in", values, "topicMsgId");
            return (Criteria) this;
        }

        public Criteria andTopicMsgIdBetween(Integer value1, Integer value2) {
            addCriterion("topic_msg_id between", value1, value2, "topicMsgId");
            return (Criteria) this;
        }

        public Criteria andTopicMsgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_msg_id not between", value1, value2, "topicMsgId");
            return (Criteria) this;
        }

        public Criteria andTopicIdIsNull() {
            addCriterion("topic_id is null");
            return (Criteria) this;
        }

        public Criteria andTopicIdIsNotNull() {
            addCriterion("topic_id is not null");
            return (Criteria) this;
        }

        public Criteria andTopicIdEqualTo(Integer value) {
            addCriterion("topic_id =", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotEqualTo(Integer value) {
            addCriterion("topic_id <>", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThan(Integer value) {
            addCriterion("topic_id >", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_id >=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThan(Integer value) {
            addCriterion("topic_id <", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThanOrEqualTo(Integer value) {
            addCriterion("topic_id <=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdIn(List<Integer> values) {
            addCriterion("topic_id in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotIn(List<Integer> values) {
            addCriterion("topic_id not in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdBetween(Integer value1, Integer value2) {
            addCriterion("topic_id between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_id not between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdIsNull() {
            addCriterion("reply_user_id is null");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdIsNotNull() {
            addCriterion("reply_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdEqualTo(Integer value) {
            addCriterion("reply_user_id =", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdNotEqualTo(Integer value) {
            addCriterion("reply_user_id <>", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdGreaterThan(Integer value) {
            addCriterion("reply_user_id >", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_user_id >=", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdLessThan(Integer value) {
            addCriterion("reply_user_id <", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("reply_user_id <=", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdIn(List<Integer> values) {
            addCriterion("reply_user_id in", values, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdNotIn(List<Integer> values) {
            addCriterion("reply_user_id not in", values, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdBetween(Integer value1, Integer value2) {
            addCriterion("reply_user_id between", value1, value2, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_user_id not between", value1, value2, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andLikedIsNull() {
            addCriterion("liked is null");
            return (Criteria) this;
        }

        public Criteria andLikedIsNotNull() {
            addCriterion("liked is not null");
            return (Criteria) this;
        }

        public Criteria andLikedEqualTo(Integer value) {
            addCriterion("liked =", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedNotEqualTo(Integer value) {
            addCriterion("liked <>", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedGreaterThan(Integer value) {
            addCriterion("liked >", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedGreaterThanOrEqualTo(Integer value) {
            addCriterion("liked >=", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedLessThan(Integer value) {
            addCriterion("liked <", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedLessThanOrEqualTo(Integer value) {
            addCriterion("liked <=", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedIn(List<Integer> values) {
            addCriterion("liked in", values, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedNotIn(List<Integer> values) {
            addCriterion("liked not in", values, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedBetween(Integer value1, Integer value2) {
            addCriterion("liked between", value1, value2, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedNotBetween(Integer value1, Integer value2) {
            addCriterion("liked not between", value1, value2, "liked");
            return (Criteria) this;
        }

        public Criteria andCommentedIsNull() {
            addCriterion("commented is null");
            return (Criteria) this;
        }

        public Criteria andCommentedIsNotNull() {
            addCriterion("commented is not null");
            return (Criteria) this;
        }

        public Criteria andCommentedEqualTo(Integer value) {
            addCriterion("commented =", value, "commented");
            return (Criteria) this;
        }

        public Criteria andCommentedNotEqualTo(Integer value) {
            addCriterion("commented <>", value, "commented");
            return (Criteria) this;
        }

        public Criteria andCommentedGreaterThan(Integer value) {
            addCriterion("commented >", value, "commented");
            return (Criteria) this;
        }

        public Criteria andCommentedGreaterThanOrEqualTo(Integer value) {
            addCriterion("commented >=", value, "commented");
            return (Criteria) this;
        }

        public Criteria andCommentedLessThan(Integer value) {
            addCriterion("commented <", value, "commented");
            return (Criteria) this;
        }

        public Criteria andCommentedLessThanOrEqualTo(Integer value) {
            addCriterion("commented <=", value, "commented");
            return (Criteria) this;
        }

        public Criteria andCommentedIn(List<Integer> values) {
            addCriterion("commented in", values, "commented");
            return (Criteria) this;
        }

        public Criteria andCommentedNotIn(List<Integer> values) {
            addCriterion("commented not in", values, "commented");
            return (Criteria) this;
        }

        public Criteria andCommentedBetween(Integer value1, Integer value2) {
            addCriterion("commented between", value1, value2, "commented");
            return (Criteria) this;
        }

        public Criteria andCommentedNotBetween(Integer value1, Integer value2) {
            addCriterion("commented not between", value1, value2, "commented");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}