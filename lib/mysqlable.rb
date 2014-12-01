require 'mysqlable/version'
require 'json'
require 'schema'
require 'model'
require 'java_builder'

module Mysqlable
  def self.parse_schemas(files)
    json_texts = files.map { |file_path| File.read file_path }
    json_schemas = json_texts.map { |text| JSON.parse text }
    json_schemas.map { |json_schema| Schema.unmarshal json_schema }
  end

  def self.build_models(schemas)
    schemas.map { |schema| Model.build schema, schemas: schemas }
  end

  def self.create_source(opts)
    out_dir = opts[:into]
    opts[:models].each do |model|
      file_path = File.join(out_dir, "JSON#{model.name.capitalize}.java")
      write_model(file_path, model)
    end
  end

  def self.write_model(file_path, model)
    File.open(file_path, 'w') do |f|
      f.write render model
    end
  end

  def self.render(model)
    JavaBuilder.new.render model
  end
end
