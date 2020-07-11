package logicController.actions;

import logicController.LogicController;
import logicController.LogicRequest;

public interface Observer {
    void observeBeforeRequest(LogicRequest request, LogicController logicController)throws  Exception;
    void observeAfterRequest(LogicRequest request, LogicController logicController);
}
