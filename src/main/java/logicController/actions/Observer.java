package logicController.actions;

import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public interface Observer {
    void observeBeforeRequest(LogicRequest request, LogicController logicController)throws  Exception;
    void observeAfterRequest(LogicRequest request, LogicController logicController);
}
