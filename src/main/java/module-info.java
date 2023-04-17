module com.tugalsan.api.storage.session {
    requires gwt.user;
    requires com.tugalsan.api.runnable;
    requires com.tugalsan.api.thread;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.list;
    requires com.tugalsan.api.time;
    exports com.tugalsan.api.storage.session.client;
    exports com.tugalsan.api.storage.session.server;
}
