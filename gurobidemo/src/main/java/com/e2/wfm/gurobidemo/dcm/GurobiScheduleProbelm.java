package com.e2.wfm.gurobidemo.dcm;

import com.e2.wfm.gurobidemo.GurobiUtil;
import com.e2.wfm.gurobidemo.dcm.data.ModelInputConfigurable;
import com.e2.wfm.gurobidemo.dcm.data.ModelInputConstant;
import com.e2.wfm.gurobidemo.dcm.data.ModelInputSingleDayTwoContactType;
import com.e2.wfm.gurobidemo.dcm.input.DCMModelInput;
import com.e2.wfm.gurobidemo.dcm.input.EmployeeSchedule;
import com.e2.wfm.gurobidemo.dcm.output.DCMModelOutput;
import com.gurobi.gurobi.GRB;
import com.gurobi.gurobi.GRBEnv;
import com.gurobi.gurobi.GRBModel;

import java.util.List;
import java.util.Map;

public class GurobiScheduleProbelm {

	public static void main(String[] args) {
		int iteration = 5;
		ModelInputConstant modelInputHelper = ModelInputConfigurable.createMiddleSize();

		DCMModelInput modelInput = modelInputHelper.createModelInput(iteration);

		GRBEnv env = null;
		GRBModel model = null;
		try {
			env = GurobiUtil.getGurobiEnvironment("dcmModel.log");
			// Create a new model
			model = new GRBModel(env);
			GurobiSolver solver = new GurobiSolver(modelInput, model);
			Map<Long, int[]> coverages = DCMModelOutput.getCoverages(modelInput.getEmployees(),
					modelInputHelper.getInterval());

			solver.initModel();
			// Optimize model
			DCMModelOutput modelOutput = null;
			for (int i = 1; i < iteration; i++) {
				System.out.println("Iteration: -------------------------------------- " + i);
				int status = solver.solve();
				if (status == GRB.Status.OPTIMAL) {
					modelOutput = solver.getValues();
					modelOutput.setCoverages(coverages);
					//System.out.println("Objective Value: " + modelOutput.getObjectiveValue());
					if(i == iteration - 1) {
						//modelOutput.writeEmployeeValues();
						break;
					}
				} else {
					System.out.println("Model is infeasible");
					System.exit(1);
				}
				List<EmployeeSchedule> schedules = modelInputHelper.getBeseSchedules(modelOutput, modelInput.getEmployees());
				solver.nextIteration(schedules, i);
				coverages = DCMModelOutput.getCoverages(schedules, modelInputHelper.getInterval());
			}
			
			//solve MIP model
			System.out.println("MIP : -------------------------------------- ");
			int status = solver.solveMip();
			if (status == GRB.Status.OPTIMAL) {
				modelOutput = solver.getValues();
				modelOutput.setCoverages(coverages);
				//System.out.println("MIP Model Objective Value: " + modelOutput.getObjectiveValue());
				//modelOutput.writeEmployeeValues();
			} else {
				System.out.println("MIP Model is infeasible");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			GurobiUtil.dispose(model);
			GurobiUtil.dispose(env);
		}
	}
}
