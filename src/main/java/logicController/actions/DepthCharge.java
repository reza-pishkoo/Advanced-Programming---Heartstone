package logicController.actions;

import logicController.LogicController;
import logicController.LogicRequest;

public class DepthCharge extends MinionObserver {

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
    }

}
