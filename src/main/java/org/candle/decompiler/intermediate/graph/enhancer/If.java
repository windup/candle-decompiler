package org.candle.decompiler.intermediate.graph.enhancer;

import org.candle.decompiler.intermediate.code.ConditionalIntermediate;
import org.candle.decompiler.intermediate.code.conditional.IfIntermediate;
import org.candle.decompiler.intermediate.graph.GraphIntermediateVisitor;
import org.candle.decompiler.intermediate.graph.context.IntermediateGraphContext;

public class If extends GraphIntermediateVisitor {

	public If(IntermediateGraphContext igc) {
		super(igc, false);
	}

	@Override
	public void visitConditionalLine(ConditionalIntermediate line) {
		
		//transform to IF block.
		
		IfIntermediate ifIntermediate = new IfIntermediate(line.getInstruction(), line.getExpression());
		ifIntermediate.setTrueTarget(line.getTrueTarget());
		ifIntermediate.setFalseTarget(line.getFalseTarget());
		
		igc.getIntermediateGraph().addVertex(ifIntermediate);
		
		//now, replace the vertex.
		igc.redirectPredecessors(line, ifIntermediate);
		igc.redirectSuccessors(line, ifIntermediate);
		
		igc.getIntermediateGraph().removeVertex(line);
	}
	

}