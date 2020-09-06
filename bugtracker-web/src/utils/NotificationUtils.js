function addSuccessNotification(notificationSystem, message) {
    const notification = notificationSystem.current;
    notification.addNotification({
        message: message,
        level: 'success'
    });
}

function addErrorNotification(notificationSystem, message) {
    const notification = notificationSystem.current;
    notification.addNotification({
        message: message,
        level: 'error'
    });
}

export const notificationUtils = {
    addErrorNotification,
    addSuccessNotification
};