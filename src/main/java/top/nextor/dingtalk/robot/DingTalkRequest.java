package top.nextor.dingtalk.robot;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 具体格式定义，请查看<a href="https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq">自定义机器人开发</a>
 */

public class DingTalkRequest {

    @JsonProperty("msgtype")
    private final MessageType messageType;

    private AT at;
    private Text text;
    private Link link;
    private Markdown markdown;
    private FeedCard feedCard;
    private ActionCard actionCard;

    private DingTalkRequest(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public AT getAt() {
        return at;
    }

    public void setAt(AT at) {
        this.at = at;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Markdown getMarkdown() {
        return markdown;
    }

    public void setMarkdown(Markdown markdown) {
        this.markdown = markdown;
    }

    public FeedCard getFeedCard() {
        return feedCard;
    }

    public void setFeedCard(FeedCard feedCard) {
        this.feedCard = feedCard;
    }

    public ActionCard getActionCard() {
        return actionCard;
    }

    public void setActionCard(ActionCard actionCard) {
        this.actionCard = actionCard;
    }

    public static DingTalkRequest of(MessageType messageType) {
        return new DingTalkRequest(messageType);
    }

    public enum MessageType {
        text,
        link,
        markdown,
        actionCard,
        feedCard
    }

    public static class AT {
        public final boolean isAtAll;
        public final List<String> atMobiles = new ArrayList<>();

        private AT(boolean isAtAll) {
            this.isAtAll = isAtAll;
        }

        public static AT of(boolean isAtAll, String... atMobiles) {
            final AT at = new AT(isAtAll);
            at.atMobiles.addAll(Arrays.asList(atMobiles));
            return at;
        }
    }

    public static class Text {
        public final String content;

        private Text(String content) {
            this.content = content;
        }

        public static Text of(String content) {
            return new Text(content);
        }
    }

    public static class Link {
        public final String title;
        public final String text;
        public final String picUrl;
        public final String messageUrl;

        private Link(String title, String text, String picUrl, String messageUrl) {
            this.title = title;
            this.text = text;
            this.picUrl = picUrl;
            this.messageUrl = messageUrl;
        }

        public static Link of(String title, String text, String picUrl, String messageUrl) {
            return new Link(title, text, picUrl, messageUrl);
        }
    }

    public static class Markdown {
        public final String title;
        public final String text;

        private Markdown(String title, String text) {
            this.title = title;
            this.text = text;
        }

        public static Markdown of(String title, String text) {
            return new Markdown(title, text);
        }
    }

    public static class FeedCard {
        public final List<Link> links = new ArrayList<>();

        private FeedCard() {
        }

        public static FeedCard of(Link... links) {
            FeedCard feedCard = new FeedCard();
            feedCard.links.addAll(Arrays.asList(links));
            return feedCard;
        }

        public static class Link {
            public final String title;
            public final String messageURL;
            public final String picURL;

            private Link(String title, String messageURL, String picURL) {
                this.title = title;
                this.messageURL = messageURL;
                this.picURL = picURL;
            }

            public static Link of(String title, String messageURL, String picURL) {
                return new Link(title, messageURL, picURL);
            }
        }
    }

    public static abstract class ActionCard {
        public final String title;
        public final String text;
        public final String hideAvatar;
        public final String btnOrientation;

        public ActionCard(String title, String text, String hideAvatar, String btnOrientation) {
            this.title = title;
            this.text = text;
            this.hideAvatar = hideAvatar;
            this.btnOrientation = btnOrientation;
        }
    }

    public static class SingleActionCard extends ActionCard {
        public final String singleTitle;
        public final String singleURL;

        private SingleActionCard(String title, String text, String hideAvatar, String btnOrientation, String singleTitle, String singleURL) {
            super(title, text, hideAvatar, btnOrientation);
            this.singleTitle = singleTitle;
            this.singleURL = singleURL;
        }

        public static SingleActionCard of(String title, String text, String hideAvatar, String btnOrientation, String singleTitle, String singleURL) {
            return new SingleActionCard(title, text, hideAvatar, btnOrientation, singleTitle, singleURL);
        }
    }

    public static class MultiActionCard extends ActionCard {
        @JsonProperty("btns")
        public final List<Action> buttons = new ArrayList<>();

        private MultiActionCard(String title, String text, String hideAvatar, String btnOrientation) {
            super(title, text, hideAvatar, btnOrientation);
        }

        public static MultiActionCard of(String title, String text, String hideAvatar, String btnOrientation, Action... buttons) {
            MultiActionCard actionCard = new MultiActionCard(title, text, hideAvatar, btnOrientation);
            actionCard.buttons.addAll(Arrays.asList(buttons));
            return actionCard;
        }

        public static class Action {
            public final String title;
            public final String actionURL;

            private Action(String title, String actionURL) {
                this.title = title;
                this.actionURL = actionURL;
            }

            public static Action of(String title, String actionURL) {
                return new Action(title, actionURL);
            }
        }
    }
}
