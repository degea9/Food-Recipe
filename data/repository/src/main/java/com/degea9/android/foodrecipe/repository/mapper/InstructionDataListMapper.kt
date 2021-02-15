package com.degea9.android.foodrecipe.repository.mapper

import com.degea9.android.foodrecipe.domain.model.Instruction
import com.degea9.foodrecipe.remote.response.InstructionResponse

class InstructionDataListMapper: ListMapper<InstructionResponse,Instruction> {


    override fun map(input: List<InstructionResponse>?): List<Instruction>? {
        return input?.map {instructionModel->
            Instruction(
                name = instructionModel.name,
                steps = MapperFactory.createListMapper<StepDataListMapper>().map(instructionModel.steps)
            )
        }
    }
}