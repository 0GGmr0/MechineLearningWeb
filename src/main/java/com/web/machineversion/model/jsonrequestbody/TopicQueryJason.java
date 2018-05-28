package com.web.machineversion.model.jsonrequestbody;

import lombok.Data;

/**
 * {
 *   "title":"Vue全家桶打造自适应 web 音乐播放器",
 *   "type":"Vue.js",
 *   "content":"<p>这是内容</p>"
 * }
 */

@Data
public class TopicQueryJason {
    //话题标题
    private String title;

    //话题主题
    private String type;

    //话题内容
    private String content;
}
